package us.donut.skuniversal.bitcoin.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import us._donut_.bitcoin.BitcoinAPI;

import javax.annotation.Nullable;

@Name("Bitcoin - Top Players")
@Description("Returns the players in order of highest balance.")
@Examples({"send \"%the top bitcoin players%\""})
public class ExprBitcoinTopPlayers extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprBitcoinTopPlayers.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] top bitcoin [bal[ance] players");
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the top bitcoin players";
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        return BitcoinAPI.getTopPlayers().stream().map(Bukkit::getOfflinePlayer).toArray(OfflinePlayer[]::new);
    }
}
