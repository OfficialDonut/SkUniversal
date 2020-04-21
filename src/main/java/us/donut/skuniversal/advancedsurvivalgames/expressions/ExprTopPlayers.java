package us.donut.skuniversal.advancedsurvivalgames.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import e.Game;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("AdvancedSurvivalGames - Top Players")
@Description("Returns names of the top players.")
@Examples({"loop survival games top players:",
		"add 1 to {_number}",
		"send \"%{_number}% - %loop-value%\"",})
public class ExprTopPlayers extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprTopPlayers.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] (top|best) [[advanced] (survival games|sg)] players");
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
        return "the top survival games players";
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        return Game.getTopPlayers().stream().map(Bukkit::getOfflinePlayer).toArray(OfflinePlayer[]::new);
    }
}
