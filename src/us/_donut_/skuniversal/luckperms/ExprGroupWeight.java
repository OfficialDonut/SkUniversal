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
import me.lucko.luckperms.api.Group;
import me.lucko.luckperms.api.Node;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("LuckPerms - Weight of Group")
@Description("Returns the weight of a group.")
@Examples({"set {default::weight} to the weight of the luckperms group \"default\""})
public class ExprGroupWeight extends SimpleExpression<Number> {

    private Expression<String> group;

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
        group = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "luckperms weight of group";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (group.getSingle(e) != null) {
            if (LuckPerms.getApi().getGroup(group.getSingle(e)).getWeight().isPresent()) {
                return new Number[]{LuckPerms.getApi().getGroup(group.getSingle(e)).getWeight().getAsInt()};
            } else {
                return new Number[]{0};
            }
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        Group groupBeingChanged = LuckPerms.getApi().getGroup(group.getSingle(e));
        if (mode == Changer.ChangeMode.SET) {
            for (Node node : groupBeingChanged.getPermissions()) {
                if (node.getPermission().split("\\.")[0].equalsIgnoreCase("weight")) {
                    groupBeingChanged.unsetPermission(node);
                }
            }
            groupBeingChanged.setPermission(LuckPerms.getApi().getNodeFactory().newBuilder("weight." + String.valueOf(delta[0])).build());
            LuckPerms.getApi().getStorage().saveGroup(groupBeingChanged);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }

}