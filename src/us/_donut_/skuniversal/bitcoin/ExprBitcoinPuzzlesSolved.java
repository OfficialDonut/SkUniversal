package us._donut_.skuniversal.bitcoin;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.util.SimpleExpression;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import us._donut_.bitcoin.Bitcoin;
import javax.annotation.Nullable;

@Name("Bitcoin - Mining Puzzles Solved")
@Description("Returns the amount of bitcoin mining puzzles solved by a player.")
@Examples({"send \"%the amount of bitcoin puzzles solved by player%\""})
public class ExprBitcoinPuzzlesSolved extends SimpleExpression<Number> {


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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the amount of bitcoin puzzles solved by player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) != null) {
            return new Number[]{Bitcoin.getAPI().getPuzzlesSolved(player.getSingle(e).getUniqueId())};
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }
}