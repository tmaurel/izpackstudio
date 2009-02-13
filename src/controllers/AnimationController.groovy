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
    * Size of the viewport
    *
    */
    def size


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
        size = scrollpane.getViewport().getSize().width
        gap = size / 2
    }



    /**
    * Calc the gap needed for a container to only show one element at once
    *
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
    */
    def slideViewPositionTo(position)
    {

        if (animation != null && animation.isRunning()) {
            animation.stop();
        }

        Point current = scrollpane.getViewport().getViewPosition()
        Point to = new Point((int) (position - getInitialPosition()), (int) current.y)
        int diff = Math.abs(to.x - current.x)
        Point to2
        Point to3

        if(current.x > to.x)
        {
            to2 = new Point((int) (to.x - diff/50), (int) to.y)
            to3 = new Point((int) (to.x + diff/100), (int) to.y)
        }
        else
        {
            to2 = new Point((int) (to.x + diff/50), (int) to.y)
            to3 = new Point((int) (to.x - diff/100), (int) to.y)
        }

        Animator.Direction direction = Animator.Direction.FORWARD
        Animator.RepeatBehavior repeatBehavior = Animator.RepeatBehavior.REVERSE
        Animator.EndBehavior behavior = Animator.EndBehavior.HOLD

        KeyTimes keyTimes = new KeyTimes(0.0f, 0.7f, 0.9f, 1.0f);
        KeyValues keyValues = KeyValues.create(
             current,
             to2,
             to3,
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
    * @return   Initial position needed for a container
    */
    def getInitialPosition()
    {
        int dec = (int) (scrollpane.getViewport().getSize().width - component.getSize().width) / 2
        return (int) dec
    }




}