using UnityEngine;
using System.Collections;

public class NotificationTest : MonoBehaviour
{
    void Awake()
    {
        LocalNotification.ClearNotifications();
    }

    public void OneTime()
    {
        LocalNotification.SendNotification(1, 5000, "Title", "Long message text", new Color32(0xff, 0x44, 0x44, 255),actions: new LocalNotification.Action("11", "test", this));
        DebugLog("OneTime");
    }

    public void OneTimeBigIcon()
    {
        LocalNotification.SendNotification(1, 5000, "Title", "Long message text with big icon", new Color32(0xff, 0x44, 0x44, 255), true, true, true, "app_icon",actions: new LocalNotification.Action("12", "test", this));
        DebugLog("OneTimeBigIcon");
    }

    public void OneTimeWithActions()
    {
        LocalNotification.Action action1 = new LocalNotification.Action("background", "In Background", this);
        action1.Foreground = false;
        LocalNotification.Action action2 = new LocalNotification.Action("foreground", "In Foreground", this);
        LocalNotification.SendNotification(1, 5000, "Title", "Long message text with actions", new Color32(0xff, 0x44, 0x44, 255), true, true, true, null, "boing", "default", action1, action2);
        DebugLog("OneTimeWithActions");
    }

    public void Repeating()
    {
        LocalNotification.SendRepeatingNotification(1, 5000, 60000, "Title", "Long message text", new Color32(0xff, 0x44, 0x44, 255));
        DebugLog("Repeating");
    }

    public void Stop()
    {
        LocalNotification.CancelNotification(1);
        DebugLog("CancelNotification");
    }

    public void OnAction(string identifier)
    {
        DebugLog("Got action " + identifier);
    }

    public void DebugLog(string message){
        Debug.LogFormat(this.GetType().Name + ".cs-->"  + message);
    }
}
