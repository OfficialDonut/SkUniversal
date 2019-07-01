package us.donut.skuniversal.skywars_cookloco.expressions;

import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.arena.ArenaManager;
import ak.CookLoco.SkyWars.player.SkyPlayer;
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

@Name("SkyWars (CookLoco) - Players in Arena")
@Description("Returns the alive players in a skywars arena.")
@Examples({"set {players::*} to the alive players in the skywars arena named \"cool\""})
public class ExprPlayers extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprPlayers.class, Player.class, ExpressionType.COMBINED, "[(all [[of] the]|the)] [alive] players in [the] [SkyWars] arena [(named|with name)] %string%");
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
        return "the players in SkyWars arena named " + name.toString(e, b);
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        Arena arena;
        if (name.getSingle(e) == null || (arena = ArenaManager.getGame(name.getSingle(e))) == null) return null;
        return arena.getAlivePlayer().stream().map(SkyPlayer::getPlayer).toArray(Player[]::new);
    }
}
