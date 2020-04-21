package us.donut.skuniversal.skywars_cookloco.expressions;

import ak.CookLoco.SkyWars.api.SkyWarsAPI;
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

@Name("SkyWars (CookLoco) - Player Kit")
@Description("Returns the skywars kit of a player.")
@Examples({"send \"%the skywars kit of player%\""})
public class ExprKit extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprKit.class, String.class, ExpressionType.COMBINED,
                "[the] [current] [SkyWars] kit of %player%",
                "%player%'s [current] [SkyWars] kit");
    }

    private Expression<Player> player;

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
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "SkyWars kit of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new String[]{SkyWarsAPI.getSkyPlayer(player.getSingle(e)).getKit().getName()};
    }
}
