package us._donut_.skuniversal.luckperms;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Group;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("LuckPerms - All Groups")
@Description("Returns the names of all groups.")
@Examples({"send \"%the luckperms groups%\""})
public class ExprAllGroups extends SimpleExpression<String> {

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
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "luckperms groups";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return LuckPerms.getApi().getGroups().stream().map(Group::getName).toArray(String[]::new);
    }

}
