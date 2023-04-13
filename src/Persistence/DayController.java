package Persistence;

import Presentation.DayView;

/**Class to connect the view to the model and handle events*/
public class DayController
{
   private CalendarModel model;

   /**Constructor method for the DayController*/
   public DayController(CalendarModel cm, DayView dv)
   {
      model = cm;

      dv.previousButtonListener(e -> {
         int selectedDay = model.getSelectedDay();
         int selectedMonth = model.getSelectedMonth();
         int selectedYear = model.getSelectedYear();
         if (selectedDay > 1)
         {
            --selectedDay;
         } else
         {
            if (selectedMonth > 0)
            {
               --selectedMonth;
            } else
            {
               selectedMonth = 11;
               --selectedYear;
               model.setCurrentYear(selectedYear);
               model.setSelectedYear(selectedYear);
            }
            model.setCurrentMonth(selectedMonth);
            model.setSelectedMonth(selectedMonth);
            model.setDaysInMonth(selectedMonth, selectedYear);
            model.setStartOfMonth(selectedMonth, selectedYear);

            selectedDay = model.getDaysInMonth();
         }
         model.setCurrentDay(selectedDay);
         model.setSelectedDay(selectedDay);
         model.notifyObservers();
      });

      dv.nextButtonListener(e -> {
         int selectedDay = model.getSelectedDay();
         int selectedMonth = model.getSelectedMonth();
         int selectedYear = model.getSelectedYear();
         int daysInMonth = model.getDaysInMonth();

         if (selectedDay < daysInMonth)
         {
            ++selectedDay;
         } else
         {
            if (selectedMonth < 11)
            {
               ++selectedMonth;
            } else
            {
               selectedMonth = 0;
               ++selectedYear;
            }
            model.setCurrentMonth(selectedMonth);
            model.setSelectedMonth(selectedMonth);
            model.setCurrentYear(selectedYear);
            model.setSelectedYear(selectedYear);
            model.setDaysInMonth(selectedMonth, selectedYear);
            model.setStartOfMonth(selectedMonth, selectedYear);
            selectedDay = 1;
         }
         model.setCurrentDay(selectedDay);
         model.setSelectedDay(selectedDay);
         model.notifyObservers();
      });
   }
}