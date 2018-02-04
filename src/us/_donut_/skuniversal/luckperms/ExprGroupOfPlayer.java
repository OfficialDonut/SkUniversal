package us._donut_.skuniversal.luckperms;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("LuckPerms - Group of Player")
@Description("Returns the group of a player.")
@Examples({"send \"%the luckperms group of player%\""})
public class ExprGroupOfPlayer extends SimpleExpression<String> {

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
        return "luckperms group of player";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) != null) {
            return new String[]{LuckPerms.getApi().getUser(player.getSingle(e).getUniqueId()).getPrimaryGroup()};
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            User user = LuckPerms.getApi().getUser(player.getSingle(e).getUniqueId());
            user.setPrimaryGroup((String) delta[0]);
            LuckPerms.getApi().getStorage().saveUser(user);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }

}