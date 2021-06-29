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
import net.luckperms.api.model.group.Group;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.luckperms.LuckPermsHook.luckpermsAPI;

@Name("LuckPerms - Permissions of Group")
@Description("Returns the permissions of a group.")
@Examples({"set {default::perms::*} to the permissions of the luckperms group \"default\""})
public class ExprGroupPermissions extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprGroupPermissions.class, String.class, ExpressionType.COMBINED,
                "[(all [[of] the]|the)] perm[ission][s] of [the] [LuckPerm[s]] group [(named|with name)] %string%",
                "[all of] [the] [LuckPerm[s]] group [(named|with name)] %string%'s perm[ission][s]");
    }

    private Expression<String> group;

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
        group = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "luckperms permissions of group " + group.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (group.getSingle(e) == null) return null;
        Group lpGroup = luckpermsAPI.getGroupManager().getGroup(group.getSingle(e));
        return lpGroup == null ? null : lpGroup.getNodes().stream().filter(node -> node.getType() == NodeType.PERMISSION).map(node -> (PermissionNode) node).map(PermissionNode::getPermission).toArray(String[]::new);
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (group.getSingle(e) == null) return;
        Group groupBeingChanged = luckpermsAPI.getGroupManager().getGroup(group.getSingle(e));
        if (groupBeingChanged == null) return;
        if (mode == Changer.ChangeMode.RESET) {
            groupBeingChanged.data().clear(node -> node.getType() == NodeType.PERMISSION);
        } else if (mode == Changer.ChangeMode.DELETE) {
            groupBeingChanged.data().clear(node -> node.getType() == NodeType.PERMISSION);
        } else if (mode == Changer.ChangeMode.ADD) {
            groupBeingChanged.data().add(PermissionNode.builder((String) delta[0]).build());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            groupBeingChanged.data().remove(PermissionNode.builder((String) delta[0]).build());
        }
        luckpermsAPI.getGroupManager().saveGroup(groupBeingChanged);
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) ? CollectionUtils.array(String.class) : null;
    }

}