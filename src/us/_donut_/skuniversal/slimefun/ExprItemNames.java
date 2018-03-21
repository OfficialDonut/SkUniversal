package us._donut_.skuniversal.slimefun;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

@Name("Slimefun - Item Names")
@Description("Returns the names of all Slimefun items")
@Examples({"send \"%the names of all Slimefun items%\""})
public class ExprItemNames extends SimpleExpression<String> {

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
        return "all of the slimefun items";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        Collection<String> items = new TreeSet<>(Collator.getInstance());
        for (SlimefunItem slimefunItem : SlimefunItem.list()) {
            items.add(slimefunItem.getID());
        }
        Path file = Paths.get("C:\\Users\\User\\Desktop\\item_names.txt");
        try {
            Files.write(file, items, Charset.forName("UTF-8"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return items.toArray(new String[items.size()]);
    }
}