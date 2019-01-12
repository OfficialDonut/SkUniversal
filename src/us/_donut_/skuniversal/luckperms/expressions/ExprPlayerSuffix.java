package us._donut_.skuniversal.luckperms.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.lucko.luckperms.api.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.luckperms.LuckPermsHook.*;

@Name("LuckPerms - Suffix of Player")
@Description("Returns the suffix of a player.")
@Examples({"send \"%the luckperms suffix of player%\""})
public class ExprPlayerSuffix extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPlayerPrefix.class, String.class, ExpressionType.COMBINED,
                "[the] [active] [LuckPerm[s]] suffix of %player%",
                "%player%'s [active] [LuckPerm[s]] suffix");
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
        return "luckperms suffix of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        User user = luckpermsAPI.getUser(player.getSingle(e).getUniqueId());
        return user == null ? null : new String[]{user.getCachedData().getMetaData(luckpermsAPI.getContextsForPlayer(player.getSingle(e))).getSuffix()};
    }

}