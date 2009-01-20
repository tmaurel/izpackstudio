package controllers

import org.jdesktop.animation.timing.Animator
import org.jdesktop.animation.timing.interpolation.KeyValues
import org.jdesktop.animation.timing.interpolation.KeyTimes
import org.jdesktop.animation.timing.interpolation.Interpolator
import java.awt.Point
import org.jdesktop.animation.timing.interpolation.KeyFrames
import org.jdesktop.animation.timing.interpolation.PropertySetter


class AnimationController {


    /**
    * Current animation
    *
    */
    def animation = null


    /**
    * Component type that will be added to the ScrollPane
    *
    */
    def component

    /**
    * ScrollPane where the animation will happen
    *
    */
    def scrollpane


    /**
    * Gap needed between each component to only display 1 at once
    *
    */
    def gap

    /**
    * Slide the given ScrollPane to the right index
    *
    * @param    compo   Component included
    * @param    sp  Scrollpane where the component is included
    */
    AnimationController(compo, sp)
    {
        component = compo
        scrollpane = sp
        gap = scrollpane.getViewport().getSize().width / 2
    }



    /**
    * Calc the gap needed for a container to only show one element at once
    *
    * @param    component   Component
    * @return   gap needed for a container
    */
    def getGap()
    {
        return gap
    }



    /**
    * Slide the given ScrollPane to the right index
    *
    * @param    index       Where you need to slide to
    * @return   gap needed for a container
    */
    def slideViewPositionTo(index)
    {

     if (animation != null && animation.isRunning()) {
            animation.stop();
        }

        Point current = scrollpane.getViewport().getViewPosition()
        Point to = new Point((int) (getInitialPosition() + index * (gap * 2 + component.getSize().width)), (int) current.y)

        Animator.Direction direction = Animator.Direction.FORWARD
        Animator.RepeatBehavior repeatBehavior = Animator.RepeatBehavior.REVERSE
        Animator.EndBehavior behavior = Animator.EndBehavior.HOLD

        KeyTimes keyTimes = new KeyTimes(0.0f, 1.0f);
        KeyValues keyValues = KeyValues.create(
             current,
             to
        );

        def keyFrames = new KeyFrames(keyValues, keyTimes, (Interpolator)null);

        animation = new Animator(2000, 1, repeatBehavior,
                new PropertySetter(scrollpane.getViewport(), "ViewPosition", keyFrames));
        animation.setResolution(0)
        animation.setStartDelay(0)
        animation.setEndBehavior(behavior)
        animation.setStartFraction(0.0f)
        animation.setStartDirection(direction)

        animation.setAcceleration(0.2f)
        animation.setDeceleration(0.8f)

        animation.start();   

    }


    /**
    * Calc the initial poisition of the container considering the component size
    * and the gap
    *
    * @param    component   Component included
    * @param    container   Container where the component is included
    * @return   gap needed for a container
    */
    def getInitialPosition()
    {
        int dec = (int) (scrollpane.getSize().width - component.getSize().width) / 2
        int pos = (int) gap - dec
        return (int) pos
    }




}