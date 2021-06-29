package us.donut.skuniversal.parties.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.api.interfaces.Party;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.parties.PartiesHook.partiesAPI;

@Name("Parties - Party Homes")
@Description("Returns the home locations of a party.")
@Examples({"send \"%the homes of the party named \"\"cool\"\"%\""})
public class ExprHome extends SimpleExpression<Location> {

    static {
        Skript.registerExpression(ExprHome.class, Location.class, ExpressionType.COMBINED,
                "[the] home[s] [loc[ation][s]] of [the] party [(named|with name)] %string%",
                "[the] party [(named|with name)] %string%'s home[s] [loc[ation][s]]");
    }

    private Expression<String> name;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "homes of the party named " + name.toString(e, b);
    }

    @Override
    @Nullable
    protected Location[] get(Event e) {
        if (name.getSingle(e) == null) return null;
        Party party = partiesAPI.getParty(name.getSingle(e));
        if (party != null) {
            return party.getHomes().stream().map(home -> new Location(Bukkit.getWorld(home.getWorld()), home.getX(), home.getY(), home.getZ(), home.getYaw(), home.getPitch())).toArray(Location[]::new);
        }
        return null;
    }
}

