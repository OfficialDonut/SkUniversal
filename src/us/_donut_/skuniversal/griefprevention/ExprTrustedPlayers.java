package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Name("GriefPrevention - Trusted Players")
@Description("Returns the trusted players of a claim.")
@Examples({"send \"%the trusted players of the claim with id (id of the basic claim at player)%\""})
public class ExprTrustedPlayers extends SimpleExpression<OfflinePlayer> {

    private Expression<Number> id;
    private ArrayList<String> trustedType;
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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
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
    public String toString(@Nullable Event e, boolean arg1) {
        return "trusted players of claim with id " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        if (id.getSingle(e) != null) {
            GriefPrevention.instance.dataStore.getClaim(id.getSingle(e).longValue()).getPermissions(builders, containers, accessors, managers);
            List<OfflinePlayer> players = new ArrayList<>();
            for (String name : trustedType) {
                players.add(Bukkit.getOfflinePlayer(name));
            }
            return players.toArray(new OfflinePlayer[players.size()]);
        } else {
            Skript.error("Must provide a number, please refer to the syntax");
            return null;
        }
    }

}