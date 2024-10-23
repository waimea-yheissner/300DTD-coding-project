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

class Room(val name: String) {
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

    // Setup some properties to hold the UI elements
    private lateinit var mainLabel: JLabel
    private lateinit var startButton: JButton
    private lateinit var goUpButton: JButton
    private lateinit var goDownButton: JButton
    private lateinit var goRightButton: JButton
    private lateinit var goLeftButton: JButton

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

        mainLabel = JLabel("Dora the explorer", SwingConstants.CENTER)
        mainLabel.bounds = Rectangle(110, -7, 201, 36)
        mainLabel.font = baseFont
        add(mainLabel)

        goUpButton = JButton("Go Up")
        goUpButton.bounds = Rectangle(150,25,120,32)
        goUpButton.font = baseFont
        goUpButton.addActionListener(this)
        add(goUpButton)

        goDownButton = JButton("Go Down")
        goDownButton.bounds = Rectangle(150,293,120,32)
        goDownButton.font = baseFont
        goDownButton.addActionListener(this)
        add(goDownButton)

        goRightButton = JButton("→")
        goRightButton.bounds = Rectangle(338,115,60,32)
        goRightButton.font = baseFont
        goRightButton.addActionListener(this)
        add(goRightButton)

        goLeftButton = JButton("←")
        goLeftButton.bounds = Rectangle(50,115,60,32)
        goLeftButton.font = baseFont
        goLeftButton.addActionListener(this)
        add(goLeftButton)
    }

    private fun setupRooms(rooms: MutableList<Room>) {
        val library = Room("Old lIBRARY")
        val school = Room("Old lIBRARY")

        library.addEast(school)

        rooms.add(library)
        rooms.add(school)

    }

    private fun startGame() {

    }

    /**
     * Handle any UI events
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            startButton -> exampleAction()
        }
    }

    /**
     * An Example Action
     */
    private fun exampleAction() {
        mainLabel.text = "You Clicked!"
    }
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


