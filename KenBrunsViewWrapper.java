package wrapp;

import android.view.ViewGroup;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.DontInheritEvents;
import anywheresoftware.b4a.BA.Events;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.BA.Version;
import anywheresoftware.b4a.keywords.Common.DesignerCustomView;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.ViewWrapper;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;
import com.flaviofaria.kenburnsview.KenBurnsView.TransitionListener;

@Version(1.0f)
@ShortName("KenBurnsView")
@Events(values={"Click", "LongClick", "TransitionStart", "TransitionEnd"})
public class KenBrunsViewWrapper extends ImageViewWrapper implements DesignerCustomView{
   private BA ba;
   private String eventName;

   public void _initialize(BA ba, Object activityClass, String EventName) {
     this.eventName = EventName.toLowerCase(BA.cul);
     this.ba = ba;
   }

   public void DesignerCreateView(PanelWrapper base, LabelWrapper lw, anywheresoftware.b4a.objects.collections.Map props) {
     final KenBurnsView kbv = new KenBurnsView(ba.context);
     setObject(kbv);
     innerInitialize(ba, eventName, true);
     PanelWrapper pw = new PanelWrapper();
     pw.setObject((ViewGroup)base.getObject().getParent());
     pw.AddView(kbv, base.getLeft(), base.getLeft(), base.getWidth(), base.getHeight());
     setBackground(base.getBackground());
     kbv.setImageDrawable(getBackground());
     base.RemoveView();
     kbv.setTransitionListener(new TransitionListener() {

       @Override
       public void onTransitionEnd(Transition transition) {
         ba.raiseEventFromUI(kbv, eventName + "_transitionend");
         
       }

       @Override
       public void onTransitionStart(Transition transition) {
         ba.raiseEventFromUI(kbv, eventName + "_transitionstart");
       }
       
     });
   }
   /**
    * Pauses the transition.
    */
   public void Pause() {
     ((KenBurnsView)getObject()).pause();
   }
   /**
    * Resumes the transition.
    */
   public void Resume() {
     ((KenBurnsView)getObject()).resume();
   }
}