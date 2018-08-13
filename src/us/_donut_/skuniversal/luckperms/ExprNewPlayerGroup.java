package us._donut_.skuniversal.luckperms;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("LuckPerms - New Group")
@Description("Returns the new group of a player in the group change event.")
public class ExprNewPlayerGroup extends SimpleExpression<String> {

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        if (!ScriptLoader.isCurrentEvent(BukkitGroupChangeEvent.class)) {
            Skript.error("You can not use the new group expression in any event but on group change.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "new LuckPerms group of player";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{((BukkitGroupChangeEvent)e).getNewGroup()};
    }
}
