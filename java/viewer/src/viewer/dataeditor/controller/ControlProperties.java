package viewer.dataeditor.controller;

/**
 * Title:        Viewer
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.0
 */

/**
 * ControlProperties interface provides names of properties
 * which are used for control data editor state
 */
public interface ControlProperties {
  /**
   * Contains total number of records in the table being edited
   */
  String NUMBER_OF_RECORDS = "RecordNumber";

  /**
   * Contains current record in the table
   */
  String RECORD_POSITION = "RecordPosition";

  /**
   * Contains boolean flag signaling if a record has just been
   * added to the table
   */
  String NEW_RECORD_ADDED = "NewRecordAdded";
}
