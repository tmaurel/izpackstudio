package views


import java.awt.Color
import javax.swing.JScrollPane
import javax.swing.JTextPane
import javax.swing.text.StyleConstants
import javax.swing.text.DefaultStyledDocument

class JInnerConsole extends JScrollPane
{
    def JTextPane textPane = new JTextPane()

    def static JInnerConsole console = null

    def doc

    def style

    def slideDown = {
        textPane.setCaretPosition(textPane.getDocument().getLength());
    }

    def writer = {
        a, b, c ->
            doc.insertString(doc.getLength(), new String(a, 0, c), style)
            slideDown()
    }

    def writerOut = {
        a, b, c ->
            StyleConstants.setForeground(style, Color.BLACK)
            writer(a, b, c)
    }

    def writerErr = {
        a, b, c ->
            StyleConstants.setForeground(style, Color.RED)
            writer(a, b, c)
    }

    def static synchronized JInnerConsole getInstance()
    {
        if (console == null)
        {
            console = new JInnerConsole()
        }
        return console
    }

    def clear()
    {
        doc = new DefaultStyledDocument()
        textPane.setStyledDocument(doc)
        style = doc.addStyle("izS", null)
    }

    JInnerConsole()
    {
        setViewportView(textPane)
        clear()
      
        def pOut = [write:writerOut] as OutputStream
        def pErr = [write:writerErr] as OutputStream

        def sOut = new PrintStream(pOut)
        def sErr = new PrintStream(pErr)

        System.setOut(sOut)
        System.setErr(sErr)
    }

}