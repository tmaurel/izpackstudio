package controllers


/**
* Controller for Projects
*
*/
class ProjectController extends Controller {


    /**
    * ProjectController Constructor
    *
    * @param    m   The model used by the constructor
    * @param    v   The view used by the constructor
    * @param    p   Parent controller
    */
    ProjectController(m = null, v = null, p = null)
    {
        super(m, v, p)
    }


    /**
    * Add a controller to panel's list
    *
    */
    public addPanel(controller) {
        model.panels.add(controller)
        parent.printPanel(controller)
    }

    /**
    * Get the model's installerframe
    *
    */
    public getInstallerFrame() {
        return model.getInstallerFrame()
    }


    /**
    * Get the model's size
    *
    */
    public getSize() {
        return model.getSize()
    }

    /**
    * Start method of the Controller
    *
    */
    public start() {

    }


    

}