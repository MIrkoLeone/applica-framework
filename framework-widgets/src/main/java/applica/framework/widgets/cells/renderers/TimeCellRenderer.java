package applica.framework.widgets.cells.renderers;

import applica.framework.widgets.Grid;
import applica.framework.widgets.GridColumn;

public class TimeCellRenderer extends BaseCellRenderer {
    protected String createTemplatePath(Grid grid, GridColumn column) {
        return "/templates/cells/time.vm";
    }
}
