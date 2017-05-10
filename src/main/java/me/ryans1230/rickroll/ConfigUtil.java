package me.ryans1230.rickroll;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@SuppressWarnings("ResultOfMethodCallIgnored")
class ConfigUtil {
    private volatile RickRoll plugin;
    ConfigUtil(RickRoll plugin) {this.plugin = plugin;}
    static Configuration c;
    private static ConfigurationProvider provider = ConfigurationProvider.getProvider(YamlConfiguration.class);

    void createRickRoll() {
        File q = plugin.getDataFolder();
        File conf = new File(q, "config.yml");
        try {
            plugin.getDataFolder().mkdir();
            conf.createNewFile();
            Configuration config = provider.load(conf);
            c = config;
            if(config.getInt("sender-cooldown") == 0) {
                config.set("sender-cooldown", 0);
            }
            if(config.getInt("receiver-cooldown") == 0) {
                config.set("receiver--cooldown", 0);
            }
            if(config.getString("prefix").isEmpty()) {
                config.set("prefix", "[&cRick Astley&r] &c{message}");
            }
            if(config.getString("sender-message").isEmpty()) {
                config.set("sender-message", "[&6Rick Astley&r] &6You have successfully sent a rick roll to &a{receiver}&6. It has been disguised as: &e{video}");
            }
            if(config.getString("receiver-message").isEmpty()) {
                config.set("receiver-message", "[&6Info&r] &6{sender} has sent a video for you to check out: &e{video}\n&aClick here to watch it now!");
            }
            if (config.getStringList("videos").isEmpty()) {
                config.set("videos", Arrays.asList("Gangnam Style - Psy", "Evolution of Dance", "See You Again - Wiz Khalifa", "Charlie bit my finger again!", "Uptown Funk - Mark Ronson", "Susan Boyle Audition 2009", "Blank Space - Taylor Swift", "The Ultimate Fails Compilation", "Hello - Adele", "Laughing Challenge (Try Not To Laugh)", "Sorry - Justin Bieber", "Shake It Off - Taylor Swift", "Bailando - Enrique Iglesias", "Lean On - Major Lazer", "Dark Horse - Katy Perry", "Roar - Katy Perry", "All About That Bass - Meghan Trainor", "Baby - Justin Bieber", "Sugar - Maroon 5", "Counting Stars - OneRepublic", "Chandelier - Sia", "Love Me LIke You Do - Ellie Goulding", "Thinking Out Loud - Ed Sheeran", "Waka Waka - Shakira", "What Do You Mean - Justin Bieber", "Love the Way You Lie - Eminem/Rihannah", "Let Her Go - Passenger", "Wake Me Up - Avicii", "Gentleman - Psy", "Worth It - Fifth Harmony", "Rude - Magic!", "On the Floor - Jennifer Lopez", "Watch Me - Silento", "Rolling In The Deep - Adele", "Thrift Shop - Macklemore & Ryan Lewis", "The Lazy Song - Bruno Mars", "Bad Blood - Taylor Swift", "Ay Vamos - J. Balvin", "All of Me - John Legend", "Burn - Ellie Goulding", "Happy - Pharrell Williams", "I Gotta Feeling - The Black Eyed Peas", "Macarena - Los Del Rio", "Yeah! - Usher", "Eye of the Tiger - Survivor", "We Found Love - Rihanna", "Low - Flo Rida", "Every Breath You Take - The Police", "Call Me Maybe - Carly Rae Jepsen", "Blurred Lines - Robin Thicke", "I Will Always Love You - Whitney Houston", "Tik Tok - Ke$ha", "Gold Digger - Kanye West", "Apologize - Timbaland", "Royals - Lorde", "I Love Rock 'n Roll - Joan Jett & the Blackhearts", "Whoomp! (There It Is) - Tag Team", "Moves Like Jagger - Maroon 5", "Billie Jean - Michael Jackson", "Abracadabra - The Steve Miller Band", "All Night Long (All Night) - Lionel Richie", "Waiting For A Girl Like You - Foreigner", "The Twist - Chubby Checker", "This is a Rick Roll, DON'T CLICK ME"));
            }
            provider.save(config, conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}