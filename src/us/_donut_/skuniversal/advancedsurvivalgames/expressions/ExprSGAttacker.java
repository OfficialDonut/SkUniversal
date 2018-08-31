package us._donut_.skuniversal.advancedsurvivalgames.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import e.SGPlayerKillEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("AdvancedSurvivalGames - Attacker")
@Description("Returns the attacker on SG Player Death event.")
public class ExprSGAttacker extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprSGAttacker.class, Player.class, ExpressionType.SIMPLE, "[the] [[advanced] (survival games|sg)] (attacker|killer)");
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
            Skript.error("You can not use survival games attacker expression in any event but on SG death event.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the survival games attacker";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        return new Player[]{((SGPlayerKillEvent)e).getKiller().getPlayer().getPlayer()};
    }
}
