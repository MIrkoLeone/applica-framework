package applica.framework.widgets.cells.renderers;

import applica.framework.widgets.Grid;
import applica.framework.widgets.GridColumn;

public class LinkCellRenderer extends BaseCellRenderer {
    protected String createTemplatePath(Grid grid, GridColumn column) {
        return "/templates/cells/text_link.vm";
    }
}
