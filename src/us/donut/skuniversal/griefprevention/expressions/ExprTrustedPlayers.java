package us.donut.skuniversal.griefprevention.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static us.donut.skuniversal.griefprevention.GriefPreventionHook.getClaim;

@Name("GriefPrevention - Trusted Players")
@Description("Returns the trusted players of a claim.")
@Examples({"send \"%the trusted players of the claim with id (id of the basic claim at player)%\""})
public class ExprTrustedPlayers extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprTrustedPlayers.class, OfflinePlayer.class, ExpressionType.COMBINED, "(0¦builder|1¦container|2¦access|3¦manager) trusted [players] (of|on) [G[rief]P[revention]] claim [with ID] %number%");
    }

    private Expression<Number> id;
    private List<String> trustedType;
    private ArrayList<String> builders = new ArrayList<>();
    private ArrayList<String> containers = new ArrayList<>();
    private ArrayList<String> accessors = new ArrayList<>();
    private ArrayList<String> managers = new ArrayList<>();

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<Number>) e[0];
        switch (pr.mark) {
            case 0: trustedType = builders;
                    break;
            case 1: trustedType = containers;
                    break;
            case 2: trustedType = accessors;
                    break;
            case 3: trustedType = managers;
                    break;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "trusted players of claim with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        builders.clear();
        containers.clear();
        accessors.clear();
        managers.clear();
        Claim claim = getClaim(id.getSingle(e).longValue());
        if (claim == null) return null;
        claim.getPermissions(builders, containers, accessors, managers);
        return trustedType.stream().map(Bukkit::getOfflinePlayer).toArray(OfflinePlayer[]::new);
    }

}