﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CQRSLiteDemo.Domain.Commands
{
    public class RemoveEmployeeFromLocationCommand : BaseCommand
    {
        public readonly int EmployeeID;
        public readonly int LocationID;

        public RemoveEmployeeFromLocationCommand(Guid id, int locationID, int employeeID)
        {
            Id = id;
            EmployeeID = employeeID;
            LocationID = locationID;
        }
    }
}
