package us.donut.skuniversal.parties.expressions;

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
import com.alessiodp.parties.api.interfaces.HomeLocation;
import com.alessiodp.parties.api.interfaces.Party;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.parties.PartiesHook.*;

@Name("Parties - Party Home")
@Description("Returns the home location of a party.")
@Examples({"send \"%the home of the party named \"\"cool\"\"%\""})
public class ExprHome extends SimpleExpression<Location> {

    static {
        Skript.registerExpression(ExprHome.class, Location.class, ExpressionType.COMBINED,
                "[the] home [loc[ation]] of [the] party [(named|with name)] %string%",
                "[the] party [(named|with name)] %string%'s home [loc[ation]]");
    }

    private Expression<String> name;

    @Override
    public boolean isSingle() {
        return true;
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
        return "home location of party named " + name.toString(e, b);
    }

    @Override
    @Nullable
    protected Location[] get(Event e) {
        if (name.getSingle(e) == null) return null;
        Party party = partiesAPI.getParty(name.getSingle(e));
        if (party != null) {
            HomeLocation homeLoc = party.getHome();
            if (homeLoc != null) {
                return new Location[]{new Location(Bukkit.getWorld(homeLoc.getWorld()), homeLoc.getX(), homeLoc.getY(), homeLoc.getZ())};
            }
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (name.getSingle(e) == null) return;
        if (mode == Changer.ChangeMode.SET) {
            Party party = partiesAPI.getParty(name.getSingle(e));
            if (party != null)
                party.setHome(new HomeLocationImpl((Location) delta[0]));
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(Location.class) : null;
    }

    private static class HomeLocationImpl implements HomeLocation {

        private Location loc;

        public HomeLocationImpl(Location loc) {
            this.loc = loc.clone();
        }

        @Override
        public String getWorld() {
            return loc.getWorld().getName();
        }

        @Override
        public void setWorld(String s) {
            loc.setWorld(Bukkit.getWorld(s));
        }

        @Override
        public double getX() {
            return loc.getX();
        }

        @Override
        public void setX(double v) {
            loc.setX(v);
        }

        @Override
        public double getY() {
            return loc.getY();
        }

        @Override
        public void setY(double v) {
            loc.setY(v);
        }

        @Override
        public double getZ() {
            return loc.getZ();
        }

        @Override
        public void setZ(double v) {
            loc.setZ(v);
        }

        @Override
        public float getYaw() {
            return loc.getYaw();
        }

        @Override
        public void setYaw(float v) {
            loc.setYaw(v);
        }

        @Override
        public float getPitch() {
            return loc.getPitch();
        }

        @Override
        public void setPitch(float v) {
            loc.setPitch(v);
        }
    }
}

