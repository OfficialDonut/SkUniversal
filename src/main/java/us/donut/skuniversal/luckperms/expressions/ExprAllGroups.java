package us.donut.skuniversal.luckperms.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.luckperms.api.model.group.Group;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.luckperms.LuckPermsHook.*;

@Name("LuckPerms - All Groups")
@Description("Returns the names of all groups.")
@Examples({"send \"%the luckperms groups%\""})
public class ExprAllGroups extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprAllGroups.class, String.class, ExpressionType.SIMPLE, "[[the] names of] [(all [[of] the]|the)] [LuckPerm[s]] groups");
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
        return "luckperms groups";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return luckpermsAPI.getGroupManager().getLoadedGroups().stream().map(Group::getName).toArray(String[]::new);
    }

}
