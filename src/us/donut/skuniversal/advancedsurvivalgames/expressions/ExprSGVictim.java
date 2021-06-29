package us.donut.skuniversal.advancedsurvivalgames.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import e.SGPlayerKillEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("AdvancedSurvivalGames - Victim")
@Description("Returns the victim on SG Player Death event.")
public class ExprSGVictim extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprSGVictim.class, Player.class, ExpressionType.SIMPLE, "[the] [[advanced] (survival games|sg)] victim");
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
        if (!ScriptLoader.isCurrentEvent(SGPlayerKillEvent.class)) {
            Skript.error("You can not use survival games victim expression in any event but on SG death event.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the survival games victim";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        return new Player[]{((SGPlayerKillEvent)e).getVictim().getPlayer().getPlayer()};
    }
}
