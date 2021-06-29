package us.donut.skuniversal.luckperms.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.luckperms.LuckPermsHook.luckpermsAPI;

@Name("LuckPerms - Permissions of Player")
@Description("Returns the permissions of a player.")
@Examples({"send \"%the luckperms permissions of player%\""})
public class ExprPlayerPermissions extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPlayerPermissions.class, String.class, ExpressionType.COMBINED,
                "[the] [LuckPerm[s]] perm[ission][s] of [player] %player%",
                "[player] %player%'s [LuckPerm[s]] perm[ission][s]");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "luckperms permissions of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        User user = luckpermsAPI.getUserManager().getUser(player.getSingle(e).getUniqueId());
        return user == null ? null : user.getNodes().stream().filter(node -> node.getType() == NodeType.PERMISSION).map(node -> (PermissionNode) node).map(PermissionNode::getPermission).toArray(String[]::new);
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (player.getSingle(e) == null) return;
        User user = luckpermsAPI.getUserManager().getUser(player.getSingle(e).getUniqueId());
        if (user == null) return;
        if (mode == Changer.ChangeMode.RESET) {
            user.data().clear(node -> node.getType() == NodeType.PERMISSION);
        } else if (mode == Changer.ChangeMode.DELETE) {
            user.data().clear(node -> node.getType() == NodeType.PERMISSION);
        } else if (mode == Changer.ChangeMode.ADD) {
            user.data().add(PermissionNode.builder((String) delta[0]).build());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            user.data().remove(PermissionNode.builder((String) delta[0]).build());
        }
       luckpermsAPI.getUserManager().saveUser(user);
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) ? CollectionUtils.array(String.class) : null;
    }

}