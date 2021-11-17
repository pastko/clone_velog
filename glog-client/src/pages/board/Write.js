import { useState, useRef } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { actionCreator as boardActions } from '../../store/reducer/board'
import Tags from '../../components/board/write/Tags'
import Title from '../../components/board/write/Title'
import '@toast-ui/editor/dist/toastui-editor.css'
import { Editor } from '@toast-ui/react-editor'
import Button from '../../components/layout/Button'
import styled from 'styled-components'

function Write(props) {
  const dispatch = useDispatch()
  const postIdx = props.match.params.idx
  const users = useSelector((state) => {
    console.log('user :: ', state.user)
  })
  const board = useSelector((state) => console.log('board :: ', state.board))
  const { posts, detailPost } = useSelector((state) => ({
    posts: state.board.posts,
    detail: state.board.detailPost,
  }))

  const [title, setTitle] = useState('')
  const [hashTags, setHashTags] = useState([])
  const editorRef = useRef()
  const onCangeTitle = (e) => {
    setTitle(e.target.value)
  }
  const goBoardSave = () => {
    const conetentHtml = editorRef.current.getInstance().getHTML()
    const contentMarkdown = editorRef.current.getInstance().getMarkdown()
    const image = conetentHtml.split('=')[1]?.split('"')[1]
    console.log('image::::: ', image)
    debugger

    if (!title || !contentMarkdown) return

    if (!contentMarkdown) return
    const post = {
      title: title,
      html: conetentHtml,
      content: contentMarkdown.replaceAll('#', ''),
      tag: hashTags,
      image: image,
    }
    debugger

    if (postIdx) {
      dispatch(boardActions.changePostAPI(postIdx, post))
    } else {
      dispatch(boardActions.addPostAPI(post))
    }
  }

  return (
    <>
      <StyledWrapper>
        <StyledHeader>
          <Title title={title} _onChange={onCangeTitle} />
          <Tags hashTags={hashTags} setHashTags={setHashTags} />
        </StyledHeader>
        <Editor
          placeholder="당신의 이야기를 시작하세요!"
          height="62vh"
          minHeight="300px"
          previewStyle="vertical"
          initialEditType="markdown"
          useCommandShortcut={true}
          previewHighlight={false}
          ref={editorRef}
        />
        <StyledBottom>
          <Button
            isDefault
            children="나가기"
            _onClick={() => {
              props.history.push('/')
            }}
          />
          <div className="right">
            <Button children="출간하기" _onClick={goBoardSave} />
          </div>
        </StyledBottom>
      </StyledWrapper>
    </>
  )
}

const StyledWrapper = styled.div`
  width: 100%;
  height: 100%;
  min-height: 0px;
  display: flex;
  flex-direction: column;
`

const StyledHeader = styled.div`
  height: 30vh;
`

const StyledBottom = styled.div`
  width: 100%;
  height: 8vh;
  position: fixed;
  bottom: 0px;
  z-index: 10;
  padding: 0 20px;
  box-shadow: #00000028 0px 0px 8px;
  background: #ffffffc5;
  display: flex;
  align-items: center;
  justify-content: space-between;
`

export default Write
