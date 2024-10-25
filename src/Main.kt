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

class Room(val name: String, val description: String) {
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
    private lateinit var goNorthButton: JButton
    private lateinit var goSouthButton: JButton
    private lateinit var goEastButton: JButton
    private lateinit var goWestButton: JButton

    /**
     * Create, build and run the UI
     */
    init {
        setupWindow()
        buildUI()
        setupRooms(rooms)



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
    private fun buildUI() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 20)

//        mainLabel = JLabel("Dora the explorer", SwingConstants.CENTER)
//        mainLabel.bounds = Rectangle(110, -7, 201, 36)
//        mainLabel.font = baseFont
//        add(mainLabel)

        currentRoomLabel = JLabel("current room", SwingConstants.CENTER)
        currentRoomLabel.bounds = Rectangle(110, 130, 201, 36)
        currentRoomLabel.font = baseFont
        add(currentRoomLabel)

        currentRoomDescLabel = JLabel("current room description", SwingConstants.CENTER)
        currentRoomDescLabel.bounds = Rectangle(110, 170, 201, 36)
        currentRoomDescLabel.font = baseFont
        add(currentRoomDescLabel)

        goNorthButton = JButton("↑")
        goNorthButton.bounds = Rectangle(150,25,120,32)
        goNorthButton.font = baseFont
        goNorthButton.addActionListener(this)
        add(goNorthButton)

        goSouthButton = JButton("↓")
        goSouthButton.bounds = Rectangle(150,293,120,32)
        goSouthButton.font = baseFont
        goSouthButton.addActionListener(this)
        add(goSouthButton)

        goEastButton = JButton("→")
        goEastButton.bounds = Rectangle(338,148,60,32)
        goEastButton.font = baseFont
        goEastButton.addActionListener(this)
        add(goEastButton)

        goWestButton = JButton("←")
        goWestButton.bounds = Rectangle(50,148,60,32)
        goWestButton.font = baseFont
        goWestButton.addActionListener(this)
        add(goWestButton)
    }

    private fun setupRooms(rooms: MutableList<Room>) {
        val library = Room("Old lIBRARY", "has a lot of books")
        val school = Room("School","")
        val house = Room("Doras house","")
        val field = Room("open field","")
        val cave = Room("Big cave","")
        val beach = Room("Beach","")
        val mountain = Room("Mountain","")


        //setting up map
        library.addEast(school)
        library.addNorth(cave)
        school.addEast(house)
        field.addNorth(cave)
        cave.addEast(beach)
        field.addSouth(mountain)


        //adding locations
        rooms.add(library)
        rooms.add(school)
        rooms.add(house)
        rooms.add(field)
        rooms.add(cave)
        rooms.add(mountain)
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
        }
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
        if (currentRoom.west != null) {
            goWestButton.isEnabled = true
        } else {
            goWestButton.isEnabled = false
        }
        currentRoomLabel.text = currentRoom.name
        currentRoomDescLabel.text = currentRoom.description

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


