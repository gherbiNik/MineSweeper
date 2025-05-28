package ch.supsi.frontend.view;

import ch.supsi.backend.business.model.AbstractModel;

public interface UncontrolledView extends DataView {

    void initialize(AbstractModel model);

}
