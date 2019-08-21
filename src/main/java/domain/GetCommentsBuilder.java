package domain;

import com.billie.core.core.BillieGetRequest;
import com.billie.core.core.MultipleResponse;
import com.billie.core.configuration.ConfigHelper;

public class GetCommentsBuilder {
    private BillieGetRequest request;

    public GetCommentsBuilder() {
        request = new BillieGetRequest(ConfigHelper.getBaseUri());
        request.path("/comments");
    }

    public MultipleResponse<CommentsResponse> run() {
        return new MultipleResponse<>(request.run(), CommentsResponse.class);
    }
}
