package Persistence;

import java.io.ObjectInputFilter.Status;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**The model for the calendar application. Stores and fetches data and performs basic*/
public class CalendarModel extends Observable
{
   // Viewing calendar
   private int currentDay;
   private int currentMonth;
   private int currentYear;
   // Month stats
   private int firstDayOfMonth;
   private int daysInMonth;
   // Selected calendar
   private int selectedDay;
   private int selectedMonth;
   private int selectedYear;
   // Events
   private Map<Date, List<Appointment>> events;
   

   /**Constructor method for CalendarModel*/
   public CalendarModel(Calendar c)
   {
      Calendar calendar = c;
      int realDay;
      selectedDay = currentDay = realDay = calendar.get(GregorianCalendar.DAY_OF_MONTH);
      int realMonth;
      selectedMonth = currentMonth = realMonth = calendar.get(GregorianCalendar.MONTH);
      int realYear;
      selectedYear = currentYear = realYear = calendar.get(GregorianCalendar.YEAR);

      GregorianCalendar calTemp = new GregorianCalendar(realYear, realMonth, 1);
      daysInMonth = calTemp.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
      firstDayOfMonth = calTemp.get(GregorianCalendar.DAY_OF_WEEK);

      events = new TreeMap<>();
   }

   /**Gets the current day*/
   public int getCurrentDay()
   {
      return currentDay;
   }
 
   /**Sets the current day */
   public void setCurrentDay(int d)
   {
      currentDay = d;
      setChanged();
   }

   /** Gets the current month */
   public int getCurrentMonth()
   {
      return currentMonth;
   }

   /**Sets the current month */
   public void setCurrentMonth(int m)
   {
      currentMonth = m;
      setChanged();
   }

   /**Gets the current year*/
   public int getCurrentYear()
   {
      return currentYear;
   }

   /**
    Sets the current year
    @param y the current year as a 4-digit integer
    */
   public void setCurrentYear(int y)
   {
      currentYear = y;
      setChanged();
   }

   /**Returns the day of the week as an integer*/
   public int getDayOfWeek()
   {
      GregorianCalendar calTemp = new GregorianCalendar(getSelectedYear(), getSelectedMonth(), getSelectedDay());
      return calTemp.get(GregorianCalendar.DAY_OF_WEEK);
   }

   /**Gets the start of the month*/
   public int getFirstDayOfMonth()
   {
      return firstDayOfMonth;
   }

   /**Sets the start of the month */
   public void setStartOfMonth(int month, int year)
   {
      GregorianCalendar calTemp = new GregorianCalendar(year, month, 1);
      firstDayOfMonth = calTemp.get(GregorianCalendar.DAY_OF_WEEK);
      setChanged();
   }

   /**Gets the number of days in the current month */
   public int getDaysInMonth()
   {
      return daysInMonth;
   }

   /**Sets the number of days in the current month*/
   public void setDaysInMonth(int month, int year)
   {
      GregorianCalendar calTemp = new GregorianCalendar(year, month, 1);
      daysInMonth = calTemp.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
      setChanged();
   }

   /**Gets the selected day*/
   public int getSelectedDay()
   {
      return selectedDay;
   }

   /**Sets the selected day*/
   public void setSelectedDay(int sd)
   {
      selectedDay = sd;
      setChanged();
   }

   /**Gets the selected month*/
   public int getSelectedMonth()
   {
      return selectedMonth;
   }

   /**Sets the selected month*/
   public void setSelectedMonth(int sm)
   {
      selectedMonth = sm;
      setChanged();
   }

   /**Gets the selected year*/
   public int getSelectedYear()
   {
      return selectedYear;
   }

   /**Sets the selected year*/
   public void setSelectedYear(int sy)
   {
      selectedYear = sy;
      setChanged();
   }

   /**
    Adds a new appointment to the model*/
   public boolean addNewEvent(Calendar start, Calendar end, String title, String status)
   {
      boolean eventAdded = false;
      if (start.before(end))
      {
         Customer newCus = new Customer("Jackie Brown", "jackiebrown@gmail.com");
         eventAdded = true;
         Appointment newEvent = new Appointment(start, end, title, status, newCus);

         // Format used for storing events [KEY]
         SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
         Date eventDate = null;
         try
         {
            // The start date defines the position in the events HashMap
            eventDate = sdf.parse(sdf.format(newEvent.getStart().getTime()));
         } catch (ParseException pe)
         {
            pe.printStackTrace();
         }

         // Locate the [KEY] position if it exists, otherwise create a new position.
         if (events.containsKey(eventDate))
         {
            // Retrieve the list of events for this date and add the new event.
            List<Appointment> eventList = events.get(eventDate);
            for (Appointment oldEvent : eventList)
            {
               // New event start is inside of old event
               if (newEvent.getStart().after(oldEvent.getStart()) &&
                  newEvent.getStart().before(oldEvent.getEnd()))
               {
                  eventAdded = false;
               }

               // New event end is inside of old event
               if (newEvent.getEnd().after(oldEvent.getStart()) &&
                  newEvent.getEnd().before(oldEvent.getEnd()))
               {
                  eventAdded = false;
               }
            }
            if (eventAdded)// Check for overlapping times here
            {
               eventList.add(newEvent);
               eventAdded = true;
               // Resort the list.
               Collections.sort(eventList, Appointment::compareTo);
            }
         } else
         {
            // If the key does not exist, we need a new event list to hold
            // events for this date.
            List<Appointment> eventList = new ArrayList<>();
            eventList.add(newEvent);
            events.put(eventDate, eventList);
            eventAdded = true;
         }
         setChanged();
      }
      return eventAdded;
   }

   /**Determines if an event has been scheduled for the given Date*/
   public boolean eventsScheduled(Date date)
   {
      boolean hasEvents = false;
      if (date != null)
      {
         SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
         try
         {
            // Ensure Date object is in the proper format to
            // compare against the map [KEY].
            date = sdf.parse(sdf.format(date));
         } catch (ParseException pe)
         {
            pe.printStackTrace();
         }
         if (events.containsKey(date))
         {
            hasEvents = true;
         }
      }
      return hasEvents;
   }


   public ArrayList<Appointment> getDayEvents(Date date)
   {
      ArrayList<Appointment> todaysEvents = new ArrayList<>();
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
      try
      {
         // Ensure Date object is in the proper format to
         // compare against the map [KEY].
         date = sdf.parse(sdf.format(date));
      }
      catch (ParseException pe)
      {
         pe.printStackTrace();
      }

      sdf.applyPattern("HH:mm");
      if (events.containsKey(date))
      {
         List<Appointment> eventList = events.get(date);
         for (Appointment e : eventList)
         {
            todaysEvents.add(e);
         }
      
      }
      else
      {
         todaysEvents = null;
      }
      return todaysEvents;
   }

   /**Returns the events Map */
   public Map<Date, List<Appointment>> getEvents()
   {
      return events;
   }

   /**Deletes the events scheduled for a given date*/
   public void deleteDayEvents(Date d)
   {
      if (events.containsKey(d) && d != null)
      {
         events.remove(d);
         setChanged();
      }
   }

   /**Adds an observer to be observed*/
   @Override
   public synchronized void addObserver(Observer o)
   {
      super.addObserver(o);
   }

   /**Remove an observed observer*/
   @Override
   public synchronized void deleteObserver(Observer o)
   {
      super.deleteObserver(o);
   }

   /**Notifies observers of changes to the model*/
   @Override
   public void notifyObservers()
   {
      super.notifyObservers();
   }
}
