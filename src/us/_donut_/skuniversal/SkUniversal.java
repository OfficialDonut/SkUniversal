package us._donut_.skuniversal;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SkUniversal extends JavaPlugin {

    private SkriptAddon addon;
    private List<String> hookedPlugins = new ArrayList<>();

    @Override
    public void onEnable() {
        addon = Skript.registerAddon(this);

        if (serverHasPlugin("AdvancedBan")) registerSyntaxes("AdvancedBan");
        if (serverHasPlugin("AdvancedSurvivalGames")) registerSyntaxes("AdvancedSurvivalGames");
        if (serverHasPlugin("Autorank")) registerSyntaxes("Autorank");
        if (serverHasPlugin("Bedwars")) registerSyntaxes("Bedwars");
        if (serverHasPlugin("Bitcoin")) registerSyntaxes("Bitcoin");
        if (serverHasPlugin("Cannons")) registerSyntaxes("Cannons");
        if (serverHasPlugin("ClearLag")) registerSyntaxes("ClearLag");
        if (serverHasPlugin("CombatLog")) registerSyntaxes("CombatLog");
        if (serverHasPlugin("GriefPrevention")) registerSyntaxes("GriefPrevention");
        if (serverHasPlugin("Lockette")) registerSyntaxes("Lockette");
        if (serverHasPlugin("LockettePro")) registerSyntaxes("LockettePro");
        if (serverHasPlugin("LuckPerms")) registerSyntaxes("LuckPerms");
        if (serverHasPlugin("LWC")) registerSyntaxes("LWC");
        if (serverHasPlugin("MinePacks")) registerSyntaxes("Minepacks");
        if (serverHasPlugin("Parties")) registerSyntaxes("Parties");
        if (serverHasPlugin("PlayerPoints")) registerSyntaxes("PlayerPoints");
        if (serverHasPlugin("PlotSquared")) registerSyntaxes("PlotSquared");
        if (serverHasPlugin("PrisonMines")) registerSyntaxes("PrisonMines");
        if (serverHasPlugin("PvPLevels")) registerSyntaxes("PvPLevels");
        if (serverHasPlugin("Shopkeepers")) registerSyntaxes("Shopkeepers");
        if (serverHasPlugin("ShopChest")) registerSyntaxes("ShopChest");
        if (serverHasPlugin("SkyWars") && getServer().getPluginManager().getPlugin("SkyWars").getDescription().getAuthors().get(0).equalsIgnoreCase("CookLoco")) registerSyntaxes("SkyWars_CookLoco");
        if (serverHasPlugin("SkyWars") && getServer().getPluginManager().getPlugin("SkyWars").getDescription().getAuthors().get(0).equalsIgnoreCase("Dabo Ross")) registerSyntaxes("SkyWars_Daboross");
        if (serverHasPlugin("Slimefun")) registerSyntaxes("Slimefun");

        getLogger().info(hookedPlugins.isEmpty() ? "Did not find any plugins to hook into." : "Hooked Plugins: " + hookedPlugins);
        getLogger().info("Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled!");
    }

    private Boolean serverHasPlugin(String pluginName) {
        return Bukkit.getPluginManager().getPlugin(pluginName) != null;
    }

    private void registerSyntaxes(String plugin) {
        hookedPlugins.add(plugin);
        try {
            addon.loadClasses("us._donut_.skuniversal." + plugin.toLowerCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("skuniversal")) {
            sender.sendMessage("");
            sender.sendMessage("§5§l§nSkUniversal v" + getDescription().getVersion());
            sender.sendMessage("");
            sender.sendMessage("§6Hooked plugins:");
            sender.sendMessage(hookedPlugins.isEmpty() ? "§eNone" : "§e" + hookedPlugins);
            sender.sendMessage("");
            sender.sendMessage("§6Plugin page:");
            sender.sendMessage("§ehttps://www.spigotmc.org/resources/skuniversal.45392/");
            sender.sendMessage("");
            sender.sendMessage("§6Documentation:");
            sender.sendMessage("§ehttps://github.com/OfficialDonut/SkUniversal/wiki");
            sender.sendMessage("");
            sender.sendMessage("§6Discord channel:");
            sender.sendMessage("§ehttps://discord.gg/UBGQhn8");
            return true;
        }
        return false;
    }
}