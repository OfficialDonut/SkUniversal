package us.donut.skuniversal.bedwars.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.MineHome.Bedwars.Game.GameAPI;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Bedwars - Existing Games")
@Description("Returns a list of all Bedwars games.")
@Examples({"loop all existing bedwars games:",
		"\tstop bedwars game loop-value"})
public class ExprGames extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprGames.class, String.class, ExpressionType.SIMPLE, "[[the] names of] [(all [[of] the]|the)] [existing] [Bedwars] games");
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "all Bedwars games";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return GameManager.getGames().stream().map(GameAPI::getName).toArray(String[]::new);
    }
}
