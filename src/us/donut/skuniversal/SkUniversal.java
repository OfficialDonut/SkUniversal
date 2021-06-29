package us.donut.skuniversal;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SkUniversal extends JavaPlugin {

    private SkriptAddon addon;
    private Set<String> hookedPlugins = new HashSet<>();

    @Override
    public void onEnable() {
        addon = Skript.registerAddon(this);
        saveDefaultConfig();

        for (String plugin : new String[]{
                "AdvancedBan", "AdvancedSurvivalGames", "Autorank", "Bedwars", "Bitcoin", "Cannons", "ClearLag",
                "CombatLog", "GriefPrevention", "Lockette", "LockettePro", "LuckPerms", "LWC", "MinePacks", "Parties",
                "PlayerPoints", "PlotSquared", "PrisonMines", "PvPLevels", "Shopkeepers", "ShopChest", "Slimefun"}) {

            if (Bukkit.getPluginManager().getPlugin(plugin) != null) {
                registerSyntaxes(plugin);
            }
        }

        if (Bukkit.getPluginManager().getPlugin("SkyWars") != null) {
            List<String> authors = Bukkit.getPluginManager().getPlugin("SkyWars").getDescription().getAuthors();
            if (authors.size() > 0) {
                String author = authors.get(0);
                if (author.equalsIgnoreCase("CookLoco")) {
                    registerSyntaxes("SkyWars_CookLoco");
                } else if (author.equalsIgnoreCase("Dabo Ross")) {
                    registerSyntaxes("SkyWars_Daboross");
                }
            }
        }

        getLogger().info(hookedPlugins.isEmpty() ? "Did not find any plugins to hook into." : "Hooked Plugins: " + hookedPlugins);
    }

    private void registerSyntaxes(String plugin) {
        if (getConfig().getBoolean("plugin-hooks." + plugin, true)) {
            try {
                addon.loadClasses("us.donut.skuniversal." + plugin.toLowerCase());
                hookedPlugins.add(plugin);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sendColored("", sender);
        sendColored("&5&l&nSkUniversal v" + getDescription().getVersion(), sender);
        sendColored("", sender);
        sendColored("&6Hooked plugins:", sender);
        sendColored(hookedPlugins.isEmpty() ? "&eNone" : "&e" + hookedPlugins, sender);
        sendColored("", sender);
        sendColored("&6Spigot:", sender);
        sendColored("&ehttps://www.spigotmc.org/resources/skuniversal.45392/", sender);
        sendColored("", sender);
        sendColored("&6Documentation:", sender);
        sendColored("&ehttps://github.com/OfficialDonut/SkUniversal/wiki", sender);
        sendColored("", sender);
        sendColored("&6Discord:", sender);
        sendColored("&ehttps://discord.gg/UBGQhn8", sender);
        return true;
    }

    private void sendColored(String message, CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}