package us.donut.skuniversal.skywars_cookloco.expressions;

import ak.CookLoco.SkyWars.events.SkyPlayerDeathEvent;
import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("SkyWars (CookLoco) - Attacker")
@Description("Returns the attacker on SkyWars Death event.")
public class ExprAttacker extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprAttacker.class, Player.class, ExpressionType.SIMPLE, "[the] SkyWars (attacker|killer)");
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
        if (!ScriptLoader.isCurrentEvent(SkyPlayerDeathEvent.class)) {
            Skript.error("You can not use skywars attacker expression in any event but on skywars death event.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the skywars attacker";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        return new Player[]{((SkyPlayerDeathEvent)e).getKiller().getPlayer()};
    }
}

