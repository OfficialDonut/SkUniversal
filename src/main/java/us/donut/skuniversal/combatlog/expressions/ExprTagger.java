package us.donut.skuniversal.combatlog.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.iiSnipez.CombatLog.Events.PlayerTagEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("ComnbatLog - Tagger")
@Description("Returns the tagger on Tag event.\n")
public class ExprTagger extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprTagger.class, Player.class, ExpressionType.SIMPLE, "[the] [CombatLog] tagger");
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        if (!ScriptLoader.isCurrentEvent(PlayerTagEvent.class)) {
            Skript.error("You can not use tagger expression in any event but on CombatLog tag!");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the tagger";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        return new Player[]{((PlayerTagEvent)e).getDamager()};
    }
}
