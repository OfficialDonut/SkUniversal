package us._donut_.skuniversal.luckperms;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.lucko.luckperms.LuckPerms;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("LuckPerms - Suffix of Player")
@Description("Returns the suffix of a player.")
@Examples({"send \"%the luckperms suffix of player%\""})
public class ExprPlayerSuffix extends SimpleExpression<String> {

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "luckperms suffix of player";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) != null) {
            return new String[]{LuckPerms.getApi().getUser(player.getSingle(e).getUniqueId()).getCachedData().getMetaData(LuckPerms.getApi().getContextsForPlayer(player.getSingle(e))).getSuffix()};
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }

}