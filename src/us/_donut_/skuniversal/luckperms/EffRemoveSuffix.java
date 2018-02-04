package us._donut_.skuniversal.luckperms;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("LuckPerms - Remove Suffix")
@Description("Removes suffix from player.")
@Examples({"remove \"[Owner]\" with priority 100 from the suffixes of player"})
public class EffRemoveSuffix extends Effect {

    private Expression<String> prefix;
    private Expression<Number> priority;
    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        prefix = (Expression<String>) e[0];
        priority = (Expression<Number>) e[1];
        player = (Expression<Player>) e[2];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "remove suffix with priority from player";
    }

    @Override
    protected void execute(Event e) {
        if (prefix.getSingle(e) != null) {
            if (priority.getSingle(e) != null) {
                if (player != null) {
                    User user = LuckPerms.getApi().getUser(player.getSingle(e).getUniqueId());
                    user.unsetPermission(LuckPerms.getApi().getNodeFactory().makeSuffixNode(priority.getSingle(e).intValue(), prefix.getSingle(e)).build());
                    LuckPerms.getApi().getStorage().saveUser(user);
                } else {
                    Skript.error("Must provide a player, please refer to the syntax");
                }
            } else {
                Skript.error("Must provide a number, please refer to the syntax");
            }
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
        }
    }

}
