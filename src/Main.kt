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
import com.formdev.flatlaf.FlatLightLaf
import java.awt.*
import java.awt.event.*
import javax.swing.*


//=============================================================================================

/**
 * GUI class
 * Defines the UI and responds to events
 */
class GUI : JFrame(), ActionListener {

    // Setup some properties to hold the UI elements
    private lateinit var exampleLabel: JLabel
    private lateinit var exampleButton: JButton

    /**
     * Create, build and run the UI
     */
    init {
        setupWindow()
        buildUI()

        // Show the app, centred on screen
        setLocationRelativeTo(null)
        isVisible = true
    }

    /**
     * Configure the main window
     */
    private fun setupWindow() {
        title = "Dora the explorer"
        contentPane.preferredSize = Dimension(414, 336,)
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

        exampleLabel = JLabel("Dora the explorer", SwingConstants.CENTER)
        exampleLabel.bounds = Rectangle(589, 237, 201, 36)
        exampleLabel.font = baseFont
        add(exampleLabel)

        exampleButton = JButton("Start the adventure")
        exampleButton.bounds = Rectangle(597,460,193,50)
        exampleButton.font = baseFont
        exampleButton.addActionListener(this)
        add(exampleButton)
    }

    /**
     * Handle any UI events
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            exampleButton -> exampleAction()
        }
    }

    /**
     * An Example Action
     */
    private fun exampleAction() {
        exampleLabel.text = "You Clicked!"
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


