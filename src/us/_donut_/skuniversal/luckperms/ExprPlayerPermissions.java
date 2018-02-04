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
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Name("LuckPerms - Permissions of Player")
@Description("Returns the permissions of a player.")
@Examples({"send \"%the luckperms permissions of player%\""})
public class ExprPlayerPermissions extends SimpleExpression<String> {

    private Expression<Player> player;

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "luckperms permissions of player";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) != null) {
            List<String> perms = new ArrayList<>();
            for (Node node : LuckPerms.getApi().getUser(player.getSingle(e).getUniqueId()).getPermissions()) {
                perms.add(node.getPermission());
            }
            return perms.toArray(new String[perms.size()]);
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        User user = LuckPerms.getApi().getUser(player.getSingle(e).getUniqueId());
        if (mode == Changer.ChangeMode.RESET) {
            user.clearNodes();
        } else if (mode == Changer.ChangeMode.DELETE) {
            user.clearNodes();
        } else if (mode == Changer.ChangeMode.ADD) {
            user.setPermission(LuckPerms.getApi().getNodeFactory().newBuilder((String) delta[0]).build());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            user.unsetPermission(LuckPerms.getApi().getNodeFactory().newBuilder((String) delta[0]).build());
        }
        LuckPerms.getApi().getStorage().saveUser(user);
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }

}