package us.donut.skuniversal.parties.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.api.interfaces.Party;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.parties.PartiesHook.*;

@Name("Parties - Party Members")
@Description("Returns the members of a party.")
@Examples({"send \"%the members of the party named \"\"cool\"\"%\""})
public class ExprMembers extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprMembers.class, OfflinePlayer.class, ExpressionType.COMBINED,
                "[(all [[of] the]|the)] members of [the] party [(named|with name)] %string%",
                "[(all [[of] the]|the)] party [(named|with name)] %string%'s members");
    }

    private Expression<String> name;

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
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "members of party named " + name.toString(e, b);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        if (name.getSingle(e) == null) return null;
        Party party = partiesAPI.getParty(name.getSingle(e));
        if (party != null) {
            return party.getMembers().stream().map(Bukkit::getOfflinePlayer).toArray(OfflinePlayer[]::new);
        }
        return new OfflinePlayer[0];
    }
}
