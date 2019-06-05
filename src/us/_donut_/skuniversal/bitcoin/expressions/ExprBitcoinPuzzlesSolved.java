package us._donut_.skuniversal.bitcoin.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import us._donut_.bitcoin.BitcoinAPI;

import javax.annotation.Nullable;

@Name("Bitcoin - Mining Puzzles Solved")
@Description("Returns the amount of bitcoin mining puzzles solved by a player.")
@Examples({"send \"%the amount of bitcoin puzzles solved by player%\""})
public class ExprBitcoinPuzzlesSolved extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprBitcoinPuzzlesSolved.class, Number.class, ExpressionType.COMBINED, "[the] [(amount|number) of] [bitcoin] [min(e|ing)] puzzles solved by %offlineplayer%");
    }

    private Expression<OfflinePlayer> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the amount of bitcoin puzzles solved by player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new Number[]{BitcoinAPI.getPuzzlesSolved(player.getSingle(e).getUniqueId())};
    }
}