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
import me.lucko.luckperms.api.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.luckperms.LuckPermsHook.*;

@Name("LuckPerms - Group of Player")
@Description("Returns the group of a player.")
@Examples({"send \"%the luckperms group of player%\""})
public class ExprGroupOfPlayer extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprGroupOfPlayer.class, String.class, ExpressionType.COMBINED,
                "[the] [LuckPerm[s]] [primary] group of %player%",
                "%player%'s [primary] [LuckPerm[s]] group");
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
        return "luckperms group of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        User user = luckpermsAPI.getUser(player.getSingle(e).getUniqueId());
        return user == null ? null : new String[]{user.getPrimaryGroup()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            if (player.getSingle(e) == null) return;
            User user = luckpermsAPI.getUser(player.getSingle(e).getUniqueId());
            if (user == null) return;
            user.setPrimaryGroup((String) delta[0]);
            luckpermsAPI.getUserManager().saveUser(user);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(String.class) : null;
    }

}