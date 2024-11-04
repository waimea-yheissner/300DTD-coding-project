/**
 * ------------------------------------------------------------------------
 * PROJECT NAME HERE
 * Level 3 programming project
 *
 * by YOUR NAME HERE
 *
 * BRIEF PROJECT DESCRIPTION HERE
 * BRIEF PROJECT DESCRIPTION HERE
 * BRIEF PROJECT DESCRIPTION HERE
 * ------------------------------------------------------------------------
 */


import com.formdev.flatlaf.FlatDarkLaf
import java.awt.*
import java.awt.event.*
import javax.swing.*


//=============================================================================================

/**
 * GUI class
 * Defines the UI and responds to events
 */

class Room(val name: String, val description: String, val goal: Boolean = false, val isTrap: Boolean = false ) {
    var north: Room? = null
    var east: Room? = null
    var south: Room? = null
    var west: Room? = null


    fun addNorth(room: Room) {
        if (north == null) {
            north = room
            room.addSouth(this)
        }
    }
    fun addSouth(room: Room) {
        if (south == null) {
            south = room
            room.addNorth(this)
        }
    }
    fun addWest(room: Room) {
        if (west == null) {
            west = room
            room.addEast(this)
        }
    }

    fun addEast(room: Room) {
        if (east == null) {
            east = room
            room.addWest(this)
        }

    }


}
class GUI : JFrame(), ActionListener {

    val rooms = mutableListOf<Room>()
    var currentRoom : Room

    // Setup some properties to hold the UI elements
    private lateinit var currentRoomLabel: JLabel
    private lateinit var currentRoomDescLabel: JLabel
    private lateinit var imageLabel: JLabel
    private lateinit var youdiedImageIcon: ImageIcon
    private lateinit var goNorthButton: JButton
    private lateinit var goSouthButton: JButton
    private lateinit var goEastButton: JButton
    private lateinit var goWestButton: JButton
    private lateinit var restartButton: JButton

    /**
     * Create, build and run the UI
     */
    init {
        loadImages()
        setupWindow()
        buildUI()
        setupRooms(rooms)
        restart()



        // Show the app, centred on screen
        setLocationRelativeTo(null)
        isVisible = true

        currentRoom = rooms.first()
        showRoom()

    }

    /**
     * Configure the main window
     */
    private fun setupWindow() {
        title = "Dora the explorer"
        contentPane.preferredSize = Dimension(420, 350,)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        layout = null

        pack()
    }

    /**
     * Populate the UI
     */

    private fun loadImages() {
        var youdiedImage = ImageIcon("src/images/youdied.jpg").image
        youdiedImage = youdiedImage.getScaledInstance(420, 290, Image.SCALE_SMOOTH)
        youdiedImageIcon = ImageIcon(youdiedImage)
    }

    private fun buildUI() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 20)

        imageLabel = JLabel()
        imageLabel.bounds =  Rectangle(0, -60, 420, 340)
        imageLabel.icon = youdiedImageIcon
        add(imageLabel)

        currentRoomLabel = JLabel("current room", SwingConstants.CENTER)
        currentRoomLabel.bounds = Rectangle(110, 130, 201, 36)
        currentRoomLabel.font = baseFont
        add(currentRoomLabel)

        currentRoomDescLabel = JLabel("current room description", SwingConstants.CENTER)
        currentRoomDescLabel.bounds = Rectangle(110, 170, 201, 72)
        currentRoomDescLabel.font = baseFont
        add(currentRoomDescLabel)

        goNorthButton = JButton("↑")
        goNorthButton.bounds = Rectangle(185,20,50,32)
        goNorthButton.font = baseFont
        goNorthButton.addActionListener(this)
        add(goNorthButton)

        goSouthButton = JButton("↓")
        goSouthButton.bounds = Rectangle(185,298,50,32)
        goSouthButton.font = baseFont
        goSouthButton.addActionListener(this)
        add(goSouthButton)

        goEastButton = JButton("→")
        goEastButton.bounds = Rectangle(341,148,50,32)
        goEastButton.font = baseFont
        goEastButton.addActionListener(this)
        add(goEastButton)

        goWestButton = JButton("←")
        goWestButton.bounds = Rectangle(30,148,50,32)
        goWestButton.font = baseFont
        goWestButton.addActionListener(this)
        add(goWestButton)

        restartButton = JButton("restart")
        restartButton.bounds = Rectangle(140,290,140,32)
        restartButton.font = baseFont
        restartButton.addActionListener(this)
        add(restartButton)
    }

    private fun setupRooms(rooms: MutableList<Room>) {
        val library = Room("Old LIBRARY", "Try to find a way home...")
        val school = Room("School","Doras school")
        val house = Room("Doras house","You made it home!!!!", true)
        val field = Room("open field","You can only see grass")
        val cave = Room("Big cave","Very dark in here")
        val beach = Room("Beach","The sea wasn't calling for you YOU DIED", false, true)
        val mountain = Room("Mountain","A mountain full of goats")
        val riddleTree = Room("Riddle Tree","What is at the end of the rainbow?")
        val trollBridge = Room("Troll Bridge","Dont look down")
        val Volcano  = Room("Volcano","It is pretty hot")
        val crocodileLake = Room("Crocodile Lake","There is too many to count")
        val gooeyGeyser = Room("Gooey Geyser","Danger: very gooey")
        val dragonMountain = Room("Dragon Mountain","The dragon lord myles sleeps on this mountain")
        val nuttyForest = Room("Nutty Forest","You can find any nuts here")
        val hauntedHouse = Room("Haunted House","There might be ghosts", false, true)


        //setting up map
        library.addEast(school)
        library.addNorth(cave)
        library.addWest(riddleTree)
        library.addSouth(trollBridge)
        trollBridge.addEast(dragonMountain)
        school.addEast(field)
        school.addNorth(beach)
        field.addSouth(nuttyForest)
        cave.addEast(beach)
        nuttyForest.addSouth(house)
        riddleTree.addNorth(hauntedHouse)
        dragonMountain.addNorth(school)


        //adding locations
        rooms.add(library)
        rooms.add(school)
        rooms.add(house)
        rooms.add(field)
        rooms.add(cave)
        rooms.add(mountain)
        rooms.add(riddleTree)
        rooms.add(Volcano)
        rooms.add(trollBridge)
        rooms.add(crocodileLake)
        rooms.add(gooeyGeyser)
        rooms.add(dragonMountain)
        rooms.add(nuttyForest)
        rooms.add(hauntedHouse)
    }
    /**
     * Handle any UI events
     */

    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            goWestButton -> goWest()
            goEastButton -> goEast()
            goNorthButton -> goNorth()
            goSouthButton -> goSouth()
            restartButton -> restart()
        }
    }

    private fun restart() {
        currentRoom = rooms.first()
        showRoom()
    }

    private fun goNorth() {
        if (currentRoom.north !=null) {
            currentRoom = currentRoom.north!!
            showRoom()
        }
    }

    private fun goSouth() {
        if (currentRoom.south !=null) {
            currentRoom = currentRoom.south!!
            showRoom()
        }
    }


    private fun goEast() {
        if (currentRoom.east !=null) {
            currentRoom = currentRoom.east!!
            showRoom()
        }
    }

    private fun goWest() {
        if (currentRoom.west != null) {
            currentRoom = currentRoom.west!!
            showRoom()
        }
    }

    private fun showRoom() {
        currentRoomLabel.text = currentRoom.name
        currentRoomDescLabel.text = "<html>${currentRoom.description}"

        if (currentRoom.isTrap) {
            // No way out!
            goWestButton.isEnabled = false
            goEastButton.isEnabled = false
            goSouthButton.isEnabled = false
            goNorthButton.isEnabled = false

            currentRoomDescLabel.isEnabled = false
            currentRoomDescLabel.isEnabled = false
            goNorthButton.isVisible = false
            goSouthButton.isVisible = false
            goEastButton.isVisible = false
            goWestButton.isVisible = false
            restartButton.isVisible = true
            imageLabel.isVisible = true


            return
        }
        else {
            imageLabel.isVisible = false
            currentRoomDescLabel.isEnabled = true
            currentRoomDescLabel.isEnabled = true
            goNorthButton.isVisible = true
            goSouthButton.isVisible = true
            goEastButton.isVisible = true
            goWestButton.isVisible = true
            restartButton.isVisible = false
        }

        if (currentRoom.goal) {
            goWestButton.isEnabled = false
            goEastButton.isEnabled = false
            goSouthButton.isEnabled = false
            goNorthButton.isEnabled = false

            goNorthButton.isVisible = false
            goSouthButton.isVisible = false
            goEastButton.isVisible = false
            goWestButton.isVisible = false

            restartButton.isVisible = true

            return
        }

        if (currentRoom.west != null) {
            goWestButton.isEnabled = true
        } else {
            goWestButton.isEnabled = false
        }

        if (currentRoom.east != null) {
            goEastButton.isEnabled = true
        } else {
            goEastButton.isEnabled = false
        }

        if (currentRoom.north != null) {
            goNorthButton.isEnabled = true
        } else {
            goNorthButton.isEnabled = false
        }

        if (currentRoom.south != null) {
            goSouthButton.isEnabled = true
        } else {
            goSouthButton.isEnabled = false
        }

        restartButton.isVisible = false
    }
    /**
     * An Example Action
     */
}


//=============================================================================================

/**
 * Launch the application
 */
fun main() {
    // Flat, Dark look-and-feel
    FlatDarkLaf.setup()

    // Create the UI
    GUI()
}


