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
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Group;
import me.lucko.luckperms.api.Node;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.luckperms.LuckPermsHook.*;

@Name("LuckPerms - Weight of Group")
@Description("Returns the weight of a group.")
@Examples({"set {default::weight} to the weight of the luckperms group \"default\""})
public class ExprGroupWeight extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprGroupWeight.class, Number.class, ExpressionType.COMBINED,
                "[the] (priority|weight) of [the] [LuckPerm[s]] group [(named|with name)] %string%",
                "[the] [LuckPerm[s]] group [(named|with name)] %string%'s (priority|weight)");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        group = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "luckperms weight of group " + group.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (group.getSingle(e) == null) return null;
        Group lpGroup = LuckPerms.getApi().getGroup(group.getSingle(e));
        if (lpGroup == null) return null;
        return lpGroup.getWeight().isPresent() ? null : new Number[]{lpGroup.getWeight().getAsInt()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (group.getSingle(e) == null) return;
        Group groupBeingChanged = LuckPerms.getApi().getGroup(group.getSingle(e));
        if (groupBeingChanged == null) return;
        if (mode == Changer.ChangeMode.SET) {
            for (Node node : groupBeingChanged.getPermissions()) {
                if (node.getPermission().split("\\.")[0].equalsIgnoreCase("weight")) groupBeingChanged.unsetPermission(node);
            }
            groupBeingChanged.setPermission(LuckPerms.getApi().getNodeFactory().newBuilder("weight." + String.valueOf(delta[0])).build());
            luckpermsAPI.getGroupManager().saveGroup(groupBeingChanged);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(Number.class) : null;
    }

}