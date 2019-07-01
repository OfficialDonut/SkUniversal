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
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.parties.PartiesHook.*;

@Name("Parties - Party Online Members")
@Description("Returns the online members of a party.")
@Examples({"send \"%the online members of the party named \"cool\"%\""})
public class ExprOnlineMembers extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprOnlineMembers.class, Player.class, ExpressionType.COMBINED,
                "[(all [[of] the]|the)] online members of [the] party [(named|with name)] %string%",
                "[(all [[of] the]|the)] party [(named|with name)] %string%'s online members");
    }

    private Expression<String> name;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "online members of party named " + name.toString(e, b);
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        if (name.getSingle(e) == null) return null;
        return partiesAPI.getPartyOnlinePlayers(name.getSingle(e)).toArray(new Player[0]);
    }
}