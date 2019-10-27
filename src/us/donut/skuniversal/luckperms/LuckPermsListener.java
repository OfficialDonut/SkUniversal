package us.donut.skuniversal.luckperms;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;
import me.lucko.luckperms.api.event.EventBus;
import me.lucko.luckperms.api.event.node.NodeMutateEvent;
import me.lucko.luckperms.api.event.user.track.UserDemoteEvent;
import me.lucko.luckperms.api.event.user.track.UserPromoteEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import us.donut.skuniversal.SkUniversal;

class LuckPermsListener {

    private SkUniversal plugin = JavaPlugin.getPlugin(SkUniversal.class);

    LuckPermsListener() {
        EventBus eventBus = LuckPerms.getApi().getEventBus();
        eventBus.subscribe(UserPromoteEvent.class, this::onPromote);
        eventBus.subscribe(UserDemoteEvent.class, this::onDemote);
        eventBus.subscribe(NodeMutateEvent.class, this::onGroupChange);
    }

    private void onPromote(UserPromoteEvent event) {
        String oldGroup = event.getGroupFrom().orElse(null);
        String newGroup = event.getGroupTo().orElse(null);
        BukkitUserPromoteEvent bukkitUserPromoteEvent = new BukkitUserPromoteEvent(Bukkit.getOfflinePlayer(event.getUser().getUuid()), oldGroup, newGroup);
        Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getServer().getPluginManager().callEvent(bukkitUserPromoteEvent));
    }

    private void onDemote(UserDemoteEvent event) {
        String oldGroup = event.getGroupFrom().orElse(null);
        String newGroup = event.getGroupTo().orElse(null);
        BukkitUserDemoteEvent bukkitUserDemoteEvent = new BukkitUserDemoteEvent(Bukkit.getOfflinePlayer(event.getUser().getUuid()), oldGroup, newGroup);
        Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getServer().getPluginManager().callEvent(bukkitUserDemoteEvent));
    }

    private void onGroupChange(NodeMutateEvent event) {
        if (event.isUser()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(((User) event.getTarget()).getUuid());
            String oldGroup = null;
            String newGroup = null;
            for (Node node : event.getDataBefore()) {
                if (node.isGroupNode())
                    oldGroup = node.getGroupName();
            }
            for (Node node : event.getDataAfter()) {
                if (node.isGroupNode())
                    newGroup = node.getGroupName();
            }
            BukkitGroupChangeEvent bukkitGroupChangeEvent = new BukkitGroupChangeEvent(player, oldGroup, newGroup);
            Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getServer().getPluginManager().callEvent(bukkitGroupChangeEvent));
        }
    }

}