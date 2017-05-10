# RickRoll
A BungeeCord plugin that allows you to rick roll your friends! Includes configurable list of videos to disguise the rick roll. 

# Commands
```
/rickroll <player> | Send a rick roll to an online player
/rickroll toggle | Temporarily toggle the ability to receive rick rolls for up to 24 hours (random hour interval picked). Requires rickroll.toggle
```
# Permissions
```
rickroll.use | Ability to use the plugin.
rickroll.toggle | Ability to temporarily toggle receiving rick rolls.
rickroll.exempt | Do not receive rick rolls at all.
```

# Configuration
Default configuration (with comments)
```
#How fast the sender can send rick rolls (in seconds). 0 to disable
sender-cooldown: 0

#How fast the receiver can be rick rolled (in seconds). O to disable
receiver-cooldown: 0

#The format for the error message. DOES NOT APPLY TO SENT RICK ROLLS. Must contain "{message}"
prefix: '[&cRick Astley&r] &c{message}'

#Message the sender will see when the rick roll is sent. Must contain "{receiver}" and "{video}
sender-message: '[&6Rick Astley&r] &6You have successfully sent a rick roll to &a{receiver}&6. It has
  been disguised as: &e{video}'
  
#Message the receiver will see when a rick roll is sent to them. Must contain "{sender}" and "{video}"
receiver-message: |-
  [&6Info&r] &6{sender} has sent a video for you to check out: &e{video}
  &aClick here to watch it now!
  
#Default video list. Videos can be added or removed at user discression
videos:
- Gangnam Style - Psy
- Evolution of Dance
- See You Again - Wiz Khalifa
- Charlie bit my finger again!
- Uptown Funk - Mark Ronson
- Susan Boyle Audition 2009
- Blank Space - Taylor Swift
- The Ultimate Fails Compilation
- Hello - Adele
- Laughing Challenge (Try Not To Laugh)
- Sorry - Justin Bieber
- Shake It Off - Taylor Swift
- Bailando - Enrique Iglesias
- Lean On - Major Lazer
- Dark Horse - Katy Perry
- Roar - Katy Perry
- All About That Bass - Meghan Trainor
- Baby - Justin Bieber
- Sugar - Maroon 5
- Counting Stars - OneRepublic
- Chandelier - Sia
- Love Me LIke You Do - Ellie Goulding
- Thinking Out Loud - Ed Sheeran
- Waka Waka - Shakira
- What Do You Mean - Justin Bieber
- Love the Way You Lie - Eminem/Rihannah
- Let Her Go - Passenger
- Wake Me Up - Avicii
- Gentleman - Psy
- Worth It - Fifth Harmony
- Rude - Magic!
- On the Floor - Jennifer Lopez
- Watch Me - Silento
- Rolling In The Deep - Adele
- Thrift Shop - Macklemore & Ryan Lewis
- The Lazy Song - Bruno Mars
- Bad Blood - Taylor Swift
- Ay Vamos - J. Balvin
- All of Me - John Legend
- Burn - Ellie Goulding
- Happy - Pharrell Williams
- I Gotta Feeling - The Black Eyed Peas
- Macarena - Los Del Rio
- Yeah! - Usher
- Eye of the Tiger - Survivor
- We Found Love - Rihanna
- Low - Flo Rida
- Every Breath You Take - The Police
- Call Me Maybe - Carly Rae Jepsen
- Blurred Lines - Robin Thicke
- I Will Always Love You - Whitney Houston
- Tik Tok - Ke$ha
- Gold Digger - Kanye West
- Apologize - Timbaland
- Royals - Lorde
- I Love Rock 'n Roll - Joan Jett & the Blackhearts
- Whoomp! (There It Is) - Tag Team
- Moves Like Jagger - Maroon 5
- Billie Jean - Michael Jackson
- Abracadabra - The Steve Miller Band
- All Night Long (All Night) - Lionel Richie
- Waiting For A Girl Like You - Foreigner
- The Twist - Chubby Checker
- This is a Rick Roll, DON'T CLICK ME
```
