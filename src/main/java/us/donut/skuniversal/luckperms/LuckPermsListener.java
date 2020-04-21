package us.donut.skuniversal.luckperms;

import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.node.NodeMutateEvent;
import net.luckperms.api.event.user.track.UserDemoteEvent;
import net.luckperms.api.event.user.track.UserPromoteEvent;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import us.donut.skuniversal.SkUniversal;

import static us.donut.skuniversal.luckperms.LuckPermsHook.*;

class LuckPermsListener {

    private SkUniversal plugin = JavaPlugin.getPlugin(SkUniversal.class);

    LuckPermsListener() {
        EventBus eventBus = luckpermsAPI.getEventBus();
        eventBus.subscribe(UserPromoteEvent.class, this::onPromote);
        eventBus.subscribe(UserDemoteEvent.class, this::onDemote);
        eventBus.subscribe(NodeMutateEvent.class, this::onGroupChange);
    }

    private void onPromote(UserPromoteEvent event) {
        String oldGroup = event.getGroupFrom().orElse(null);
        String newGroup = event.getGroupTo().orElse(null);
        BukkitUserPromoteEvent bukkitUserPromoteEvent = new BukkitUserPromoteEvent(Bukkit.getOfflinePlayer(event.getUser().getUniqueId()), oldGroup, newGroup);
        Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getPluginManager().callEvent(bukkitUserPromoteEvent));
    }

    private void onDemote(UserDemoteEvent event) {
        String oldGroup = event.getGroupFrom().orElse(null);
        String newGroup = event.getGroupTo().orElse(null);
        BukkitUserDemoteEvent bukkitUserDemoteEvent = new BukkitUserDemoteEvent(Bukkit.getOfflinePlayer(event.getUser().getUniqueId()), oldGroup, newGroup);
        Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getPluginManager().callEvent(bukkitUserDemoteEvent));
    }

    private void onGroupChange(NodeMutateEvent event) {
        if (event.isUser()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(((User) event.getTarget()).getUniqueId());
            String oldGroup = null;
            String newGroup = null;
            for (Node node : event.getDataBefore()) {
                if (node.getType() == NodeType.INHERITANCE)
                    oldGroup = ((InheritanceNode) node).getGroupName();
            }
            for (Node node : event.getDataAfter()) {
                if (node.getType() == NodeType.INHERITANCE)
                    newGroup = ((InheritanceNode) node).getGroupName();
            }
            BukkitGroupChangeEvent bukkitGroupChangeEvent = new BukkitGroupChangeEvent(player, oldGroup, newGroup);
            Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getPluginManager().callEvent(bukkitGroupChangeEvent));
        }
    }

}