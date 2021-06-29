package us.donut.skuniversal.luckperms.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import us.donut.skuniversal.luckperms.BukkitGroupChangeEvent;

import javax.annotation.Nullable;

@Name("LuckPerms - Old Group")
@Description("Returns the old group of a player in the group change event.")
public class ExprOldPlayerGroup extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprOldPlayerGroup.class, String.class, ExpressionType.SIMPLE,
                "[the] old [LuckPerm[s]] group [of [the] player]",
                "[the] player's old [LuckPerm[s]] group");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        if (!ScriptLoader.isCurrentEvent(BukkitGroupChangeEvent.class)) {
            Skript.error("You can not use the old group expression in any event but on group change.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "old LuckPerms group of player";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{((BukkitGroupChangeEvent)e).getOldGroup()};
    }
}
